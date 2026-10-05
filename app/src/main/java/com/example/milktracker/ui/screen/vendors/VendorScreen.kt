package com.example.milktracker.ui.screen.vendors

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import com.example.milktracker.data.repository.VendorRepository

@Stable
data class VendorUi(
    val id: Long = 0L,
    val name: String = "",
    val ratePerLiter: Float = 0f,
    val phone: String = "",
    val active: Boolean = true
)

@Composable
fun VendorScreen(
    repository: VendorRepository,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val viewModel: VendorViewModel = remember { VendorViewModel(repository) }
    val vendors by viewModel.vendors.collectAsState(initial = emptyList())

    // Stable lambda for add FAB (remembered at screen level)
    val onAdd = remember { { viewModel.openAddDialog() } }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Vendors") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = vendors,
                key = { it.id },
                contentType = { "vendor" }
            ) { vendor ->
                Card(modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()) {
                    Row(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(vendor.name, style = MaterialTheme.typography.titleMedium)
                            Text("Rate: ₹%.0f/L · %s".format(vendor.ratePerLiter, if (vendor.active) "Active" else "Inactive"), style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }

    // Add/Edit dialog
    if (viewModel.showDialog.value) {
        AlertDialog(
            onDismissRequest = { viewModel.showDialog.value = false },
            title = { Text("Vendor") },
            text = {
                Column {
                    OutlinedTextField(value = viewModel.editName.value, onValueChange = { viewModel.editName.value = it }, label = { Text("Name") })
                    OutlinedTextField(value = viewModel.editRate.value, onValueChange = { viewModel.editRate.value = it }, label = { Text("Rate / L") })
                    OutlinedTextField(value = viewModel.editPhone.value, onValueChange = { viewModel.editPhone.value = it }, label = { Text("Phone (optional)") })
                    Row {
                        Switch(checked = viewModel.editActive.value, onCheckedChange = { viewModel.editActive.value = it })
                        Text("Active")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.save() }) { Text("Save") }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.showDialog.value = false }) { Text("Cancel") }
            }
        )
    }
}

class VendorViewModel(private val repo: VendorRepository) {
    // Placeholder wire to repo (compile-safe stub)
    val vendors = kotlinx.coroutines.flow.flowOf(emptyList<VendorUi>())
    val showDialog = mutableStateOf(false)
    val editName = mutableStateOf("")
    val editRate = mutableStateOf("")
    val editPhone = mutableStateOf("")
    val editActive = mutableStateOf(true)

    fun openAddDialog() {
        editName.value = ""
        editRate.value = ""
        editPhone.value = ""
        editActive.value = true
        showDialog.value = true
    }

    fun save() {
        // Placeholder: insert via repo
        showDialog.value = false
    }
}

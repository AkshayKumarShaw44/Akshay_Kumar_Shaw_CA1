package com.example.akshay_kumar_shaw_ca1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.akshay_kumar_shaw_ca1.ui.theme.Akshay_Kumar_Shaw_CA1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Akshay_Kumar_Shaw_CA1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("John Doe") }
    var regNo by remember { mutableStateOf("12200001") }
    var phone by remember { mutableStateOf("9988776655") }
    var email by remember { mutableStateOf("john.doe@example.com") }
    var bio by remember { mutableStateOf("Student | Developer") }

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Profile Information", fontSize = 22.sp, style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))
        Text("Name: $name"); Text("Reg No: $regNo"); Text("Phone: $phone"); Text("Email: $email"); Text("Bio: $bio")
        Spacer(Modifier.height(30.dp))
        Button(onClick = { showDialog = true }) { Text("Edit Profile") }
    }

    if (showDialog) {
        var tempName by remember { mutableStateOf(name) }
        var tempRegNo by remember { mutableStateOf(regNo) }
        var tempPhone by remember { mutableStateOf(phone) }
        var tempEmail by remember { mutableStateOf(email) }
        var tempBio by remember { mutableStateOf(bio) }

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Update Profile") },
            text = {
                Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = tempName, onValueChange = { tempName = it }, label = { Text("Name") })
                    OutlinedTextField(value = tempRegNo, onValueChange = { tempRegNo = it }, label = { Text("Reg No") })
                    OutlinedTextField(value = tempPhone, onValueChange = { tempPhone = it }, label = { Text("Phone") })
                    OutlinedTextField(value = tempEmail, onValueChange = { tempEmail = it }, label = { Text("Email") })
                    OutlinedTextField(value = tempBio, onValueChange = { tempBio = it }, label = { Text("Bio") })
                }
            },
            confirmButton = {
                Button(onClick = {
                    name = tempName; regNo = tempRegNo; phone = tempPhone; email = tempEmail; bio = tempBio
                    showDialog = false
                }) { Text("Edit") }
            },
            dismissButton = { OutlinedButton(onClick = { showDialog = false }) { Text("Cancel") } }
        )
    }
}

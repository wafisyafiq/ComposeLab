
package com.example.composelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold { innerPadding ->
                RegisterScreen(Modifier.padding(innerPadding))
            }
        }
    }


}

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Create Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {

            Button(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {},
                modifier = Modifier.weight(1f)
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun StudentList(modifier: Modifier = Modifier) {
    val students = remember {
        List(50) {
            Student("Student ${it + 1}", 50 + it)
        }
    }

    var grid by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Button(onClick = { grid = !grid }) {
            Text("Switch Layout")
        }

        if (grid) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.weight(1f)
            ) {
                items(students) { student ->
                    StudentCard(student)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(students) { student ->
                    StudentCard(student)
                }
            }
        }
    }
}
data class Student(
    val name: String,
    val score: Int
)
@Composable
fun StudentCard(student: Student) {
    Column {
        Text(student.name)
        Text("Marks: ${student.score}")
    }
}




package com.example.a1stproject
// this application was developed following a YouTube tutorial on Andriod app development.
//the code was adapted and modified for learning purposes.
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a1stproject.ui.theme._1stProjectTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _1stProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    project(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun project(modifier: Modifier = Modifier) {
    var timeInput by remember { mutableStateOf(value = "" ) }
    var suggestion by remember { mutableStateOf(value = "") }
    var errorMessage by remember { mutableStateOf( value = "") }

    Column(modifier = modifier.padding(all = 16.dp)) {
        TextField(
            value = timeInput,
            onValueChange = { timeInput = it},
            label = { Text("Enter time of the day") }
        )
        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (validateInput(timeInput)) {
                suggestion = getProject(timeInput)
                errorMessage = ""
            } else {
                suggestion = ""
                errorMessage = "Invalid time. Try: morning, afternoon, dinner."
            }
        }) {
            Text("Get Suggestions")
        }
        Spacer(Modifier.height(8.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
        } else {
            Text(text = "Suggestion: $suggestion")
        }
        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            timeInput = ""
            suggestion = ""
            errorMessage = ""
        }) {
            Text("reset")
        }
    }
}
// Validation Logic
fun validateInput(input: String): Boolean {
    val validTimes = listOf("morning","afternoon","dinner")
    return validTimes.contains(input.lowercase())
}


// project logic
fun getProject(time: String): String {
    return when (time.lowercase()) {
        "morning" -> "send a 'Good Morning' text to a family member."
        "afternoon "->"share a funny meme or interesting link with a friend"
        "dinner" -> "call a friend or relative for a 5-minutes catch-up."
        else -> "" // Handled by validation
    }
}

@Preview(showBackground = true)
@Composable
fun project() {
    _1stProjectTheme {
        project()
    }
}
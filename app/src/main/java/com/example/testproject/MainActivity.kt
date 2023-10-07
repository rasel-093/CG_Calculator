package com.example.testproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testproject.ui.theme.TestProjectTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val context = applicationContext
                    CGPA(context)
                }
            }
        }
    }
}


fun printCG(cgList: MutableList<String>, context: Context){
    var Count = 0.0
    var Total_GPA = 0.0
    var CG : Double

    for(x in 0..7) {
        if (cgList[x].length != 0) {
            Count++
            Total_GPA += cgList[x].toDouble()
        }
    }
    CG = Total_GPA/Count
//    Log.d("CGPA",CG.toString())
//    Log.d("Completed",Count.toString())

    Toast.makeText(context,"CGPA = ${String.format("%.2f",CG)}\n Completed Semester = ${Count.toInt()}",Toast.LENGTH_SHORT).show()
    Count = 0.0
}

@Composable
fun CGPA(context: Context){
    //Log.d("Starting","Starting of cgpa function compose")

    var cgList by remember { mutableStateOf<MutableList<String>>(MutableList(8){""}) }


    var cg1 by rememberSaveable { mutableStateOf("") }
    var cg2 by rememberSaveable { mutableStateOf("") }
    var cg3 by rememberSaveable { mutableStateOf("") }
    var cg4 by rememberSaveable { mutableStateOf("") }
    var cg5 by rememberSaveable { mutableStateOf("") }
    var cg6 by rememberSaveable { mutableStateOf("") }
    var cg7 by rememberSaveable { mutableStateOf("") }
    var cg8 by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .widthIn(10.dp, 10.dp)
            .background(color = Color.DarkGray)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "CGPA Calculator",
                fontSize = 20.sp
            )
        }
        //Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Cyan)
        ) {
//            for(x in 0..7){
//                rowComponent(semester = (x+1).toString(), value = cgList[x], onValueChange = {cgList[x] = it} )
//            }
            rowComponent(semester = "1st", value = cg1, onValueChange = { cg1 = it})
            rowComponent(semester = "2nd", value = cg2, onValueChange = { cg2 = it })
            rowComponent(semester = "3rd", value = cg3, onValueChange = { cg3 = it })
            rowComponent(semester = "4th", value = cg4, onValueChange = { cg4 = it })
            rowComponent(semester = "5th", value = cg5, onValueChange = { cg5 = it })
            rowComponent(semester = "6th", value = cg6, onValueChange = { cg6 = it })
            rowComponent(semester = "7th", value = cg7, onValueChange = { cg7 = it })
            rowComponent(semester = "8th", value = cg8, onValueChange = { cg8 = it })

            cgList[0] = cg1
            cgList[1] = cg2
            cgList[2] = cg3
            cgList[3] = cg4
            cgList[4] = cg5
            cgList[5] = cg6
            cgList[6] = cg7
            cgList[7] = cg8
        }
        //Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            //result(cg, Count.toString())
            Button(
                border = BorderStroke(2.dp, color = Color.Gray),
                colors = ButtonDefaults.buttonColors(contentColor = Color.DarkGray),
                onClick = {
                   printCG(cgList,context).toString()
//                    Log.d("cg","CG Inside button")
                }
            ) {
                Text(
                    text = "Calculate",
                    fontSize = 20.sp
                )
            }
        }
    }
}
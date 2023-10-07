package com.example.testproject

import android.accessibilityservice.AccessibilityService.SoftKeyboardController
import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import kotlin.math.round
import androidx.compose.material3.Text as Text

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun rowComponent(semester: String, value: String?,onValueChange: (String) ->Unit){
    var cg = value.toString()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(border = BorderStroke(1.dp, Color.Black))
            .padding(2.dp)
            .clip(RoundedCornerShape(10.dp)),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            Text(
                text = semester,
                fontSize = 16.sp)
        }
        Column {
            TextField(
                value = cg,
                onValueChange ={text->
//                    Log.d("text",text)
//                    Log.d("Length",text.length.toString())

                    when(text.length) {
                        1 -> {
                            if (text.isDigitsOnly() && Regex("[2-4]$").find(text)!=null) {
                                onValueChange(text)
                            }
                        }

                        2 -> {
                            if (Regex("[2-4]\\.").find(text) != null) {
                                onValueChange(text)
                            }
                        }
                        3-> {
                            if( Regex("[2-3]\\.[0-9]").find(text)!=null || Regex("4.0").find(text)!=null){
                                onValueChange(text)
                            }
                        }
                        4-> {
                            if( Regex("[2-4]\\.[0-9]{2}").find(text)!=null || Regex("4.00").find(text)!=null){
                                onValueChange(text)
                            }
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    }

                               },
                label = {Text(text = "Enter GPA")},
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()}
                )
            )
        }
        //Text(text = "Clear",modifier = Modifier.clickable(enabled = true, onClick = {cg =""}) )
    }
}
@Preview
@Composable
fun rowPreview(){
    rowComponent(semester = "1st","3.45",{})
}
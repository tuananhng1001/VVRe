package com.example.basekotlinproject.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class BaseViewModel(open val app: Application) : AndroidViewModel(app) {

}
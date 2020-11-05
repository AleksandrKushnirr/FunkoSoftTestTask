package com.example.funkosofttesttask.data.web.pojo

import com.google.gson.annotations.SerializedName


data class InfoJson (

	@SerializedName("data") val data : Data,
	@SerializedName("ad") val ad : Ad
)
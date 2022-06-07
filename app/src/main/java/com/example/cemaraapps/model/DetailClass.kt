package com.example.cemaraapps.model

class DetailClass {

    public var namaFamily:String
    public var kode:String

    constructor(nama: String, kode: String) {
        this.namaFamily = nama
        this.kode = kode
    }

    constructor() : this ("", "")

}
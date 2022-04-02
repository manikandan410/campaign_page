package com.example.campaignpage

class ModelVideo2 {

    //variables, use names as in firebase
    var id2: String? = null
    var title2: String? = null
    var timestamp2: String? = null
    var videoUri2: String? = null


    //constructor
    constructor(){
        //firebase requires empty constructor

    }

    constructor(id2: String?, title2: String?, timestamp2: String?, videoUri2: String?) {
        this.id2 = id2
        this.title2 = title2
        this.timestamp2 = timestamp2
        this.videoUri2 = videoUri2

    }

}
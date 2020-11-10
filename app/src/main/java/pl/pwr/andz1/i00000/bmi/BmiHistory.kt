package pl.pwr.andz1.i00000.bmi

import java.io.Serializable

class BmiHistory  (
        private var last10List: ArrayList<BmiResultObject> = arrayListOf<BmiResultObject>()
) : Serializable{

    fun add(bmiObject : BmiResultObject) {
        if(last10List.size > 9)
            last10List = last10List.drop(1) as ArrayList<BmiResultObject>

        last10List.add(bmiObject)
    }

    fun get() : ArrayList<BmiResultObject>{
        return last10List
    }

    fun set(listSave : ArrayList<BmiResultObject>) {
        last10List = listSave
    }
}
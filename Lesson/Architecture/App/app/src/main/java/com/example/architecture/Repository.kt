package com.example.architecture

class Repository{
    private var names = ArrayList<String>()

    init{
        names.add("Akzhol")
        names.add("Nazira")
        names.add("Arsen")
        names.add("Abilmansur")
        names.add("Aizere")
        names = ArrayList(names.shuffled())
    }

    fun getNames(): ArrayList<String>{
        return names;
    }
}
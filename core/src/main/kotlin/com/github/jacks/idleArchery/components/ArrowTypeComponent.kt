package com.github.jacks.idleArchery.components

enum class ArrowType {
    UNDEFINED, NORMAL, FIRE, FROST, SPLIT, POISON, MAGIC, BLOOD
}

enum class ArrowEffect {
    NO_EFFECT, FROST_EFFECT, SPLIT_EFFECT, POISON_EFFECT, MAGIC_EFFECT, BLOOD_EFFECT
}

data class ArrowTypeComponent(
    var arrowType : ArrowType = ArrowType.UNDEFINED,
    var arrowTypeName : String = "",
    var arrowTypeDescription : String = "",
    var fireRate : Float = 0f,
    var damage : Float = 0f,
    var effect : ArrowEffect = ArrowEffect.NO_EFFECT
)

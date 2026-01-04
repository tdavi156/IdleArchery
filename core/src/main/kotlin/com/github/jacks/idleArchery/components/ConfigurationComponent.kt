package com.github.jacks.idleArchery.components

enum class ConfigurationType {
    UNDEFINED, PLAYER;
}

data class ConfigurationComponent(
    var configurationName : String = "",
    var configurationType : ConfigurationType = ConfigurationType.UNDEFINED
)

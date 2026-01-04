package com.github.jacks.idleArchery.systems

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.github.jacks.idleArchery.components.ConfigurationComponent
import com.github.jacks.idleArchery.components.ConfigurationType.*
import com.github.jacks.idleArchery.events.InitializeGameEvent
import com.github.quillraven.fleks.AllOf
import com.github.quillraven.fleks.ComponentMapper
import com.github.quillraven.fleks.Entity
import com.github.quillraven.fleks.IteratingSystem
import ktx.log.logger
import ktx.preferences.flush
import ktx.preferences.get
import ktx.preferences.set

@AllOf([ConfigurationComponent::class])
class InitializeGameSystem(
    private val configurationComponents : ComponentMapper<ConfigurationComponent>
) : EventListener, IteratingSystem() {


    val preferences : Preferences by lazy { Gdx.app.getPreferences("idleArcheryPrefs") }

    override fun onTickEntity(entity: Entity) {
        with(configurationComponents[entity]) {
            when(configurationType) {
                PLAYER -> {

                }
                UNDEFINED -> {
                    log.debug { "$configurationName has an UNDEFINED type." }
                    return@with
                }
            }
        }
        world.remove(entity)
    }

    override fun handle(event: Event?): Boolean {
        when(event) {
            is InitializeGameEvent -> {
                if (!preferences["is_game_initialized", false]) {
                    preferences.clear()
                    setupPreferences()
                }
            }
            else -> return false
        }
        return true
    }

    private fun setupPreferences() {
        preferences.flush {
            this["is_game_initialized"] = true
        }
    }

    companion object {
        private val log = logger<InitializeGameSystem>()
    }
}

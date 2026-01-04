package com.github.jacks.idleArchery.systems

import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.github.quillraven.fleks.IntervalSystem

class RenderSystem(
    private val stage : Stage
) : EventListener, IntervalSystem() {

    override fun onTick() {
        with(stage) {
            viewport.apply()
            act(deltaTime)
            draw()
        }
    }

    override fun handle(event: Event?): Boolean {
        return true
    }
}

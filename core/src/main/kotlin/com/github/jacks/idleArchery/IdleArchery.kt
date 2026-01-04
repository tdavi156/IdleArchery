package com.github.jacks.idleArchery

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.github.jacks.idleArchery.screens.GameScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.disposeSafely
import ktx.preferences.flush
import java.math.MathContext
import java.math.RoundingMode

class IdleArchery : KtxGame<KtxScreen>(), EventListener {

    private val batch : Batch by lazy { SpriteBatch() }
    val gameStage : Stage by lazy { Stage(FitViewport(16f, 9f), batch) }
    val uiStage : Stage by lazy { Stage(ScreenViewport(), batch) }
    val preferences : Preferences by lazy { Gdx.app.getPreferences("idleArcheryPrefs") }
    private val resetOnStart : Boolean = true

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        if (resetOnStart) {
            preferences.flush {
                this.clear()
            }
        }

        //loadSkin()
        gameStage.addListener(this)
        addScreen(GameScreen(this))
        setScreen<GameScreen>()
    }

    override fun render() {
        clearScreen(0f, 0f, 0f, 1f)
        currentScreen.render(Gdx.graphics.deltaTime)
    }

    override fun resize(width: Int, height: Int) {
        gameStage.viewport.update(width, height, true)
        uiStage.viewport.update(width, height, true)
    }

    override fun dispose() {
        super.dispose()
        gameStage.disposeSafely()
        uiStage.disposeSafely()
        batch.disposeSafely()
        //disposeSkin()
    }

    override fun handle(event: Event?): Boolean {
        return true
    }

    companion object {
        const val FRAMES_PER_SECOND_FLOAT : Float = 60f
        const val FRAMES_PER_SECOND_INT : Int = 60
        val MATH_CONTEXT : MathContext = MathContext(10, RoundingMode.HALF_UP)
    }
}

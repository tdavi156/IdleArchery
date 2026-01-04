package com.github.jacks.idleArchery.screens

import com.badlogic.gdx.ai.GdxAI
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.github.jacks.idleArchery.IdleArchery
import com.github.jacks.idleArchery.events.InitializeGameEvent
import com.github.jacks.idleArchery.events.fire
import com.github.jacks.idleArchery.systems.RenderSystem
import com.github.jacks.idleArchery.ui.views.backgroundView
import com.github.quillraven.fleks.World
import com.github.quillraven.fleks.world
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.log.logger
import ktx.scene2d.actors

class GameScreen(game : IdleArchery) : KtxScreen {

    private val preferences = game.preferences
    private val gameStage = game.gameStage
    private val uiStage = game.uiStage
    //private val textureAtlas : TextureAtlas = TextureAtlas("assets/graphics/gameObjects.atlas")

    private val entityWorld : World = world {
        injectables {
            add(gameStage)
            add("uiStage", uiStage)
            //add(textureAtlas)
        }

        components {
            //add<ComponentListener>()
            /*


             */
        }

        systems {
            add<RenderSystem>()
            //add<ArrowSpawnSystem>()
        //add<System>()
            /*
                add<AudioSystem>()
                add<AnimationSystem>()
                add<FloatingTextSystem>()
             */
        }
    }

    init {
        uiStage.actors {
            log.debug { "Stage is initialized"}

            // background, actor.get(0)
            backgroundView()

            // main UI, actor.get(1)
            // maineUIView()


        }
    }

    override fun show() {
        log.debug { "GameScreen is shown" }

        entityWorld.systems.forEach { system ->
            if (system is EventListener) {
                gameStage.addListener(system)
            }
        }

        gameStage.fire(InitializeGameEvent())
        //entityWorld.system<PortalSystem>().setMap("map_1")
        //PlayerKeyboardInputProcessor(entityWorld, gameStage, uiStage)
        //gdxInputProcessor(uiStage)
    }

    override fun render(delta: Float) {
        val deltaTime = delta.coerceAtMost(0.25f)
        GdxAI.getTimepiece().update(deltaTime)
        entityWorld.update(deltaTime)
    }

    override fun dispose() {
        //textureAtlas.disposeSafely()
        entityWorld.dispose()
    }

    companion object {
        private val log = logger<GameScreen>()
    }
}

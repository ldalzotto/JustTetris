package main.java.com.ldz.test;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ldz.physics.body.BodyCreator;
import com.ldz.physics.body.BodyWorldManager;
import com.ldz.physics.domain.DynamicBody;
import com.ldz.physics.domain.World;

public class PhysicsApplication extends ApplicationAdapter {

    private OrthographicCamera camera;
    private FitViewport viewport;
    private ShapeRenderer shapeRenderer;

    private World world = new World(0.01f, new Vector2(0, -50f));

    @Override
    public void create() {
        camera = new OrthographicCamera(70, 70);
        viewport = new FitViewport(400, 400, camera);
        viewport.apply();
        shapeRenderer = new ShapeRenderer();

        DynamicBody dynamicBody = BodyCreator.createDynamicBody(new Rectangle(0, 0, 10, 10)).applyVelocity(new
                Vector2(50,
                50));

        BodyWorldManager.addBody(dynamicBody);
        BodyWorldManager.addStaticBody(new Rectangle(-10, -10, 999999, 5));
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
        viewport.update(width, height);
    }

    @Override
    public void render() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Float deltaTime = Gdx.graphics.getDeltaTime();

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        Vector2 currentPos = BodyWorldManager.bodyContainer().head().geometryStatus().centerOfMass();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        world.update(deltaTime);
        shapeRenderer.point(currentPos.x, currentPos.y, 0);

        shapeRenderer.end();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

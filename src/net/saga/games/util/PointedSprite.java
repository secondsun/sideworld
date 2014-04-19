/**
 * Copyright Hoyt Summers Pittman III
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.saga.games.util;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.TiledTextureRegion;

import android.graphics.PointF;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 *
 * See http://games.greggman.com/game/programming_m_c__kids/
 *
 * @author Summers
 *
 */
public class PointedSprite extends AnimatedSprite {

    /**
     * Top left of hitbox
     */
    public final PointF _a;

    /**
     * bottom left of hitbox
     */
    public final PointF _b;

    /**
     * topright of hitbox
     */
    public final PointF _c;

    /**
     * bottom right of hitbox
     */
    public final PointF _d;

    /**
     * middle top
     */
    public final PointF _t;

    /**
     * middle bottom, used for ground position, is the position of the sprite
     */
    public final PointF _h;

    /**
     * bottom left for standing
     */
    public final PointF _1;

    /**
     * bottom right for standing
     */
    public final PointF _2;

    public int frameAdjust = 0;
    private int _frameAdjust = 0;
    
    public final float tileWidth;
    public final float tileHeight;
    
    public PointedSprite(float pX, float pY, float pTileWidth,
            float pTileHeight, TiledTextureRegion pTiledTextureRegion,
            VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion, vertexBufferObjectManager);
        _a = new PointF(0, pTileHeight);
        _b = new PointF(0, 0);
        _c = new PointF(pTileWidth, pTileHeight);
        _d = new PointF(pTileWidth, 0);
        _h = new PointF(pTileWidth / 2, 0);
        _t = new PointF(pTileWidth / 2, pTileHeight);
        _1 = _b;
        _2 = _d;
        tileHeight = pTileHeight;
        tileWidth = pTileWidth;
    }

    public PointedSprite(float pX, float pY, float pTileWidth,
            float pTileHeight, TiledTextureRegion pTiledTextureRegion,
            PointF _a, PointF _b, PointF _c, PointF _d, PointF _t, PointF _h,
            PointF _1, PointF _2, VertexBufferObjectManager vertexBufferObjectManager) {
        super(pX, pY, pTileWidth, pTileHeight, pTiledTextureRegion, vertexBufferObjectManager);
        this._a = _a;
        this._1 = _1;
        this._2 = _2;
        this._b = _b;
        this._c = _c;
        this._d = _d;
        this._t = _t;
        this._h = _h;
        tileHeight = pTileHeight;
        tileWidth = pTileWidth;
    }

    public PointedSprite(PointedSprite projectileSprite) {
        super(projectileSprite.mX,
                projectileSprite.mY,
                projectileSprite.tileWidth,
                projectileSprite.tileHeight,
                projectileSprite.getTiledTextureRegion(),
                projectileSprite.getVertexBufferObjectManager());
        this._a = projectileSprite._a;
        this._1 = projectileSprite._1;
        this._2 = projectileSprite._2;
        this._b = projectileSprite._b;
        this._c = projectileSprite._c;
        this._d = projectileSprite._d;
        this._t = projectileSprite._t;
        this._h = projectileSprite._h;
        tileHeight = projectileSprite.tileHeight;
        tileWidth = projectileSprite.tileWidth;
    }

    public PointF getAdjustedPosition() {
        PointF position = new PointF(mX + mWidth - _h.x, mY + mHeight - _h.y);
        return position;
    }

    public PointF getAdjustedHeadPosition() {
        PointF position = new PointF(mX + mWidth - _t.x, mY + mHeight - _t.y);
        return position;
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.setCurrentTileIndex(super.getCurrentTileIndex() - _frameAdjust);
        _frameAdjust = frameAdjust;
        super.onManagedUpdate(pSecondsElapsed);
        super.setCurrentTileIndex(super.getCurrentTileIndex() + _frameAdjust);
    }

}

package net.saga.games.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.saga.games.sidewol.util.controller.Controller;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;

import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityComparator;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.IEntityParameterCallable;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.transformation.Transformation;

/**
 *
 *
 * Wraps a {@link PointedSprite} and "binds" control logic.
 *
 * This is just to get away from constructor hell on the Pointed Sprite
 *
 * @author Summers
 *
 */
public class ControllablePointedSprite implements IEntity {

    public final PointedSprite _sprite;

    private final ArrayList<Controller<?>> _controllers;

    public ControllablePointedSprite(PointedSprite _sprite) {
        super();
        this._sprite = _sprite;
        this._controllers = new ArrayList<Controller<?>>();
    }

    public void addController(Controller<?> controller) {
        _controllers.add(controller);
    }

    public void removeController(Controller<?> controller) {
        _controllers.remove(controller);
    }

    public List<Controller<?>> getControllers() {
        // TODO Auto-generated method stub
        return Collections.unmodifiableList(this._controllers);
    }

    @Override
    public boolean isVisible() {
        return _sprite.isVisible();
    }

    @Override
    public void setVisible(boolean arg0) {
        _sprite.setVisible(arg0);

    }

    @Override
    public boolean isIgnoreUpdate() {
        return _sprite.isIgnoreUpdate();

    }

    @Override
    public void setIgnoreUpdate(boolean arg0) {
        _sprite.setIgnoreUpdate(arg0);

    }

    @Override
    public boolean isChildrenVisible() {
        return _sprite.isChildrenVisible();

    }

    @Override
    public void setChildrenVisible(boolean arg0) {
        _sprite.setChildrenVisible(arg0);

    }

    @Override
    public boolean isChildrenIgnoreUpdate() {
        return _sprite.isChildrenIgnoreUpdate();

    }

    @Override
    public void setChildrenIgnoreUpdate(boolean arg0) {
        _sprite.setChildrenIgnoreUpdate(arg0);

    }

    @Override
    public int getTag() {
        return _sprite.getTag();

    }

    @Override
    public void setTag(int arg0) {
        _sprite.setTag(arg0);

    }

    @Override
    public int getZIndex() {
        return _sprite.getZIndex();

    }

    @Override
    public void setZIndex(int arg0) {
        _sprite.setZIndex(arg0);

    }

    @Override
    public boolean hasParent() {
        return _sprite.hasParent();

    }

    @Override
    public IEntity getParent() {
        return _sprite.getParent();

    }

    @Override
    public void setParent(IEntity arg0) {
        _sprite.setParent(arg0);

    }

    @Override
    public IEntity getRootEntity() {
        return _sprite.getRootEntity();

    }

    @Override
    public float getX() {
        return _sprite.getX();

    }

    @Override
    public float getY() {
        return _sprite.getY();

    }

    @Override
    public void setX(float arg0) {
        _sprite.setX(arg0);

    }

    @Override
    public void setY(float arg0) {
        _sprite.setY(arg0);

    }

    @Override
    public void setPosition(IEntity arg0) {
        _sprite.setPosition(arg0);

    }

    @Override
    public void setPosition(float arg0, float arg1) {
        _sprite.setPosition(arg0, arg1);

    }

    @Override
    public float getWidth() {
        return _sprite.getWidth();

    }

    @Override
    public float getHeight() {
        return _sprite.getHeight();

    }

    @Override
    public float getWidthScaled() {
        return _sprite.getWidthScaled();

    }

    @Override
    public float getHeightScaled() {
        return _sprite.getHeightScaled();

    }

    @Override
    public void setHeight(float arg0) {
        _sprite.setHeight(arg0);

    }

    @Override
    public void setWidth(float arg0) {
        _sprite.setWidth(arg0);

    }

    @Override
    public void setSize(float arg0, float arg1) {
        _sprite.setSize(arg0, arg1);

    }

    @Override
    public float getOffsetCenterX() {
        return _sprite.getOffsetCenterX();

    }

    @Override
    public float getOffsetCenterY() {
        return _sprite.getOffsetCenterY();

    }

    @Override
    public void setOffsetCenterX(float arg0) {
        _sprite.setOffsetCenterX(arg0);

    }

    @Override
    public void setOffsetCenterY(float arg0) {
        _sprite.setOffsetCenterY(arg0);

    }

    @Override
    public void setOffsetCenter(float arg0, float arg1) {
        _sprite.setOffsetCenter(arg0, arg1);

    }

    @Override
    public boolean isRotated() {
        return _sprite.isRotated();

    }

    @Override
    public float getRotation() {
        return _sprite.getRotation();

    }

    @Override
    public void setRotation(float arg0) {
        _sprite.setRotation(arg0);

    }

    @Override
    public float getRotationCenterX() {
        return _sprite.getRotationCenterX();

    }

    @Override
    public float getRotationCenterY() {
        return _sprite.getRotationCenterY();

    }

    @Override
    public void setRotationCenterX(float arg0) {
        _sprite.setRotationCenterX(arg0);

    }

    @Override
    public void setRotationCenterY(float arg0) {
        _sprite.setRotationCenterY(arg0);

    }

    @Override
    public void setRotationCenter(float arg0, float arg1) {
        _sprite.setRotationCenter(arg0, arg1);

    }

    @Override
    public boolean isScaled() {
        return _sprite.isScaled();

    }

    @Override
    public float getScaleX() {
        return _sprite.getScaleX();

    }

    @Override
    public float getScaleY() {
        return _sprite.getScaleY();

    }

    @Override
    public void setScaleX(float arg0) {
        _sprite.setScaleX(arg0);

    }

    @Override
    public void setScaleY(float arg0) {
        _sprite.setScaleY(arg0);

    }

    @Override
    public void setScale(float arg0) {
        _sprite.setScale(arg0);

    }

    @Override
    public void setScale(float arg0, float arg1) {
        _sprite.setScale(arg0, arg1);

    }

    @Override
    public float getScaleCenterX() {
        return _sprite.getScaleCenterX();

    }

    @Override
    public float getScaleCenterY() {
        return _sprite.getScaleCenterY();

    }

    @Override
    public void setScaleCenterX(float arg0) {
        _sprite.setScaleCenterX(arg0);

    }

    @Override
    public void setScaleCenterY(float arg0) {
        _sprite.setScaleCenterY(arg0);

    }

    @Override
    public void setScaleCenter(float arg0, float arg1) {
        _sprite.setScaleCenter(arg0, arg1);

    }

    @Override
    public boolean isSkewed() {
        return _sprite.isSkewed();

    }

    @Override
    public float getSkewX() {
        return _sprite.getSkewX();

    }

    @Override
    public float getSkewY() {
        return _sprite.getSkewY();

    }

    @Override
    public void setSkewX(float arg0) {
        _sprite.setSkewX(arg0);

    }

    @Override
    public void setSkewY(float arg0) {
        _sprite.setSkewY(arg0);

    }

    @Override
    public void setSkew(float arg0) {
        _sprite.setSkew(arg0);

    }

    @Override
    public void setSkew(float arg0, float arg1) {
        _sprite.setSkew(arg0, arg1);

    }

    @Override
    public float getSkewCenterX() {
        return _sprite.getSkewCenterX();

    }

    @Override
    public float getSkewCenterY() {
        return _sprite.getSkewCenterY();

    }

    @Override
    public void setSkewCenterX(float arg0) {
        _sprite.setSkewCenterX(arg0);

    }

    @Override
    public void setSkewCenterY(float arg0) {
        _sprite.setSkewCenterY(arg0);

    }

    @Override
    public void setSkewCenter(float arg0, float arg1) {
        _sprite.setSkewCenter(arg0, arg1);

    }

    @Override
    public boolean isRotatedOrScaledOrSkewed() {
        return _sprite.isRotatedOrScaledOrSkewed();

    }

    @Override
    public void setAnchorCenterX(float arg0) {
        _sprite.setAnchorCenterX(arg0);

    }

    @Override
    public void setAnchorCenterY(float arg0) {
        _sprite.setAnchorCenterY(arg0);

    }

    @Override
    public void setAnchorCenter(float arg0, float arg1) {
        _sprite.setAnchorCenter(arg0, arg1);

    }

    @Override
    public float getRed() {
        return _sprite.getRed();

    }

    @Override
    public float getGreen() {
        return _sprite.getGreen();

    }

    @Override
    public float getBlue() {
        return _sprite.getBlue();

    }

    @Override
    public float getAlpha() {
        return _sprite.getAlpha();

    }

    @Override
    public Color getColor() {
        return _sprite.getColor();

    }

    @Override
    public void setRed(float arg0) {
        _sprite.setRed(arg0);

    }

    @Override
    public void setGreen(float arg0) {
        _sprite.setGreen(arg0);

    }

    @Override
    public void setBlue(float arg0) {
        _sprite.setBlue(arg0);

    }

    @Override
    public void setAlpha(float arg0) {
        _sprite.setAlpha(arg0);

    }

    @Override
    public void setColor(Color arg0) {
        _sprite.setColor(arg0);

    }

    @Override
    public void setColor(int arg0) {
        _sprite.setColor(arg0);

    }

    @Override
    public void setColor(float arg0, float arg1, float arg2) {
        _sprite.setColor(arg0, arg1, arg2);

    }

    @Override
    public void setColor(float arg0, float arg1, float arg2, float arg3) {
        _sprite.setColor(arg3, arg3, arg3, arg3);
    }

    @Override
    public float[] getSceneCenterCoordinates() {
        return _sprite.getSceneCenterCoordinates();

    }

    @Override
    public float[] getSceneCenterCoordinates(float[] arg0) {
        return _sprite.getSceneCenterCoordinates(arg0);

    }

    @Override
    public float[] convertLocalCoordinatesToParentCoordinates(float arg0, float arg1) {
        return _sprite.convertLocalCoordinatesToParentCoordinates(arg0, arg1);

    }

    @Override
    public float[] convertLocalCoordinatesToParentCoordinates(float arg0, float arg1, float[] arg2) {
        return _sprite.convertLocalCoordinatesToParentCoordinates(arg0, arg1, arg2);

    }

    @Override
    public float[] convertLocalCoordinatesToParentCoordinates(float[] arg0, float[] arg1) {
        return _sprite.convertLocalCoordinatesToParentCoordinates(arg0, arg1);

    }

    @Override
    public float[] convertParentCoordinatesToLocalCoordinates(float arg0, float arg1) {
        return _sprite.convertParentCoordinatesToLocalCoordinates(arg0, arg1);

    }

    @Override
    public float[] convertParentCoordinatesToLocalCoordinates(float arg0, float arg1, float[] arg2) {
        return _sprite.convertParentCoordinatesToLocalCoordinates(arg0, arg1, arg2);

    }

    @Override
    public float[] convertParentCoordinatesToLocalCoordinates(float[] arg0) {
        return _sprite.convertParentCoordinatesToLocalCoordinates(arg0);

    }

    @Override
    public float[] convertParentCoordinatesToLocalCoordinates(float[] arg0, float[] arg1) {
        return _sprite.convertParentCoordinatesToLocalCoordinates(arg0, arg1);

    }

    @Override
    public float[] convertLocalCoordinatesToSceneCoordinates(float arg0, float arg1) {
        return _sprite.convertLocalCoordinatesToSceneCoordinates(arg0, arg1);

    }

    @Override
    public float[] convertLocalCoordinatesToSceneCoordinates(float arg0, float arg1, float[] arg2) {
        return _sprite.convertLocalCoordinatesToSceneCoordinates(arg0, arg1, arg2);

    }

    @Override
    public float[] convertLocalCoordinatesToSceneCoordinates(float[] arg0) {
        return _sprite.convertLocalCoordinatesToSceneCoordinates(arg0);

    }

    @Override
    public float[] convertLocalCoordinatesToSceneCoordinates(float[] arg0, float[] arg1) {
        return _sprite.convertLocalCoordinatesToSceneCoordinates(arg0, arg1);

    }

    @Override
    public float[] convertSceneCoordinatesToLocalCoordinates(float arg0, float arg1) {
        return _sprite.convertSceneCoordinatesToLocalCoordinates(arg0, arg1);

    }

    @Override
    public float[] convertSceneCoordinatesToLocalCoordinates(float arg0, float arg1, float[] arg2) {
        return _sprite.convertSceneCoordinatesToLocalCoordinates(arg0, arg1, arg2);

    }

    @Override
    public float[] convertSceneCoordinatesToLocalCoordinates(float[] arg0) {
        return _sprite.convertSceneCoordinatesToLocalCoordinates(arg0);

    }

    @Override
    public float[] convertSceneCoordinatesToLocalCoordinates(float[] arg0, float[] arg1) {
        return _sprite.convertSceneCoordinatesToLocalCoordinates(arg0, arg1);

    }

    @Override
    public Transformation getLocalToSceneTransformation() {
        return _sprite.getLocalToSceneTransformation();

    }

    @Override
    public Transformation getSceneToLocalTransformation() {
        return _sprite.getSceneToLocalTransformation();

    }

    @Override
    public Transformation getLocalToParentTransformation() {
        return _sprite.getLocalToParentTransformation();

    }

    @Override
    public Transformation getParentToLocalTransformation() {
        return _sprite.getParentToLocalTransformation();

    }

    @Override
    public int getChildCount() {
        return _sprite.getChildCount();

    }

    @Override
    public void onAttached() {
        _sprite.onAttached();

    }

    @Override
    public void onDetached() {
        _sprite.onDetached();

    }

    @Override
    public void attachChild(IEntity arg0) {
        _sprite.attachChild(arg0);

    }

    @Override
    public IEntity getChildByTag(int arg0) {
        return _sprite.getChildByTag(arg0);
    }

    @Override
    public IEntity getChildByMatcher(IEntityMatcher arg0) {
        return _sprite.getChildByMatcher(arg0);
    }

    @Override
    public IEntity getChildByIndex(int arg0) {
        return _sprite.getChildByIndex(arg0);
    }

    @Override
    public IEntity getFirstChild() {
        return _sprite.getFirstChild();

    }

    @Override
    public IEntity getLastChild() {
        return _sprite.getLastChild();

    }

    @Override
    public ArrayList<IEntity> query(IEntityMatcher arg0) {
        return _sprite.query(arg0);
    }

    @Override
    public IEntity queryFirst(IEntityMatcher arg0) {
        return _sprite.queryFirst(arg0);
    }

    @Override
    public <L extends List<IEntity>> L query(IEntityMatcher arg0, L arg1) {
        return _sprite.query(arg0, arg1);
    }

    @Override
    public <S extends IEntity> S queryFirstForSubclass(IEntityMatcher arg0) {
        return _sprite.queryFirstForSubclass(arg0);
    }

    @Override
    public <S extends IEntity> ArrayList<S> queryForSubclass(IEntityMatcher arg0) throws ClassCastException {
        return _sprite.queryForSubclass(arg0);
    }

    @Override
    public <L extends List<S>, S extends IEntity> L queryForSubclass(IEntityMatcher arg0, L arg1) throws ClassCastException {
        return _sprite.queryForSubclass(arg0, arg1);
    }

    @Override
    public void sortChildren() {
        _sprite.sortChildren();
    }

    @Override
    public void sortChildren(boolean pImmediate) {
        _sprite.sortChildren(pImmediate);
    }

    @Override
    public void sortChildren(IEntityComparator pEntityComparator) {
        _sprite.sortChildren(pEntityComparator);
    }

    @Override
    public boolean detachSelf() {
        return _sprite.detachSelf();

    }

    @Override
    public boolean detachChild(IEntity pEntity) {
        return _sprite.detachChild(pEntity);
    }

    @Override
    public IEntity detachChild(int pTag) {
        return _sprite.detachChild(pTag);
    }

    @Override
    public IEntity detachChild(IEntityMatcher pEntityMatcher) {
        return _sprite.detachChild(pEntityMatcher);
    }

    @Override
    public boolean detachChildren(IEntityMatcher pEntityMatcher) {
        return _sprite.detachChildren(pEntityMatcher);
    }

    @Override
    public void detachChildren() {
        _sprite.detachChildren();
    }

    @Override
    public void callOnChildren(IEntityParameterCallable arg0) {
        _sprite.callOnChildren(arg0);

    }

    @Override
    public void callOnChildren(IEntityParameterCallable arg0, IEntityMatcher arg1) {
        _sprite.callOnChildren(arg0, arg1);

    }

    @Override
    public void registerUpdateHandler(IUpdateHandler arg0) {
        _sprite.registerUpdateHandler(arg0);

    }

    @Override
    public boolean unregisterUpdateHandler(IUpdateHandler arg0) {
        return _sprite.unregisterUpdateHandler(arg0);
    }

    @Override
    public boolean unregisterUpdateHandlers(IUpdateHandlerMatcher arg0) {
        return _sprite.unregisterUpdateHandlers(arg0);
    }

    @Override
    public int getUpdateHandlerCount() {
        return _sprite.getUpdateHandlerCount();

    }

    @Override
    public void clearUpdateHandlers() {
        _sprite.clearUpdateHandlers();

    }

    @Override
    public void registerEntityModifier(IEntityModifier arg0) {
        _sprite.registerEntityModifier(arg0);

    }

    @Override
    public boolean unregisterEntityModifier(IEntityModifier arg0) {
        return _sprite.unregisterEntityModifier(arg0);
    }

    @Override
    public boolean unregisterEntityModifiers(IEntityModifier.IEntityModifierMatcher arg0) {
        return _sprite.unregisterEntityModifiers(arg0);
    }

    @Override
    public int getEntityModifierCount() {
        return _sprite.getEntityModifierCount();

    }

    @Override
    public void resetEntityModifiers() {
        _sprite.resetEntityModifiers();

    }

    @Override
    public void clearEntityModifiers() {
        _sprite.clearEntityModifiers();
    }

    @Override
    public boolean isCullingEnabled() {
        return _sprite.isCullingEnabled();
    }

    @Override
    public void setCullingEnabled(boolean arg0) {
        _sprite.setCullingEnabled(arg0);
    }

    @Override
    public boolean isCulled(Camera pCamera) {
        return _sprite.isCulled(pCamera);
    }

    @Override
    public boolean collidesWith(IEntity arg0) {
        return _sprite.collidesWith(arg0);
    }

    @Override
    public void setUserData(Object arg0) {
        _sprite.setUserData(arg0);

    }

    @Override
    public Object getUserData() {
        return _sprite.getUserData();

    }

    @Override
    public void toString(StringBuilder arg0) {
        _sprite.toString(arg0);

    }

    @Override
    public void onDraw(GLState arg0, Camera arg1) {
        _sprite.onDraw(arg0, arg1);

    }

    @Override
    public void onUpdate(float arg0) {
        _sprite.onUpdate(arg0);
    }

    @Override
    public void reset() {
        _sprite.reset();
    }

    @Override
    public boolean isDisposed() {
        return _sprite.isDisposed();
    }

    @Override
    public void dispose() throws AlreadyDisposedException {
        _sprite.dispose();
    }

    @Override
    public boolean contains(float arg0, float arg1) {
        return _sprite.contains(arg1, arg1);
    }

    @Override
    public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        return _sprite.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
    }

    @Override
    public float[] convertLocalCoordinatesToParentCoordinates(float[] arg0) {
        return _sprite.convertLocalCoordinatesToParentCoordinates(arg0);
    }

}

package net.saga.games.util;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.ui.activity.SimpleBaseGameActivity;


/**
 *
 * This class "shims" roboguice into abstract game stuff.
 *
 * @author Summers
 *
 */
public abstract class SidewolBaseGameActivity extends SimpleBaseGameActivity {

    protected final GamePad gamePad = new GamePad();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        gamePad.down(event);
        return true;
    }

    @Override
    public final boolean onKeyUp(int keyCode, KeyEvent event) {
        gamePad.up(event);
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
    }

}

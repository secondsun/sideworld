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

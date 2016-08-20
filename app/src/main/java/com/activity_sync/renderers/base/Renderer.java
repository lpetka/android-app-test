/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
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

package com.activity_sync.renderers.base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * Core class in this library. Base class created to work as a root ViewHolder in the classic
 * ListView / Adapter implementation. This entity will be extended by other Renderer classes in
 * order to show items into the screen.
 * <p>
 * A Renderer have to encapsulate the presentation logic for ech row of your ListView/RecyclerView.
 * <p>
 * Every Renderer have inside the view is rendering and the content is using to get the info.
 * <p>
 * If you used to use RecyclerView extensions of this class are going to replace ViewHolder
 * implementations.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public abstract class Renderer<T> implements Cloneable
{

    private View rootView;
    private T content;
    private int layoutRes;

    public Renderer(int layoutRes)
    {
        this.layoutRes = layoutRes;
    }

    /**
     * Method called when the renderer is going to be created. This method has the responsibility of
     * inflate the xml layout using the layoutInflater and the parent ViewGroup, set itself to the
     * tag and call setUpView and hookListeners methods.
     *
     * @param content        to render. If you are using Renderers with RecyclerView widget the content will
     *                       be null in this method.
     * @param layoutInflater used to inflate the view.
     * @param parent         used to inflate the view.
     */
    public void onCreate(T content, LayoutInflater layoutInflater, ViewGroup parent)
    {
        this.content = content;
        this.rootView = inflate(layoutInflater, parent);
        if (rootView == null)
        {
            throw new RuntimeException(
                    "Renderer instances have to return a not null view in inflateView method");
        }
        this.rootView.setTag(this);
    }

    /**
     * Method called when the Renderer has been recycled. This method has the responsibility of
     * update the content stored in the renderer.
     *
     * @param content to render.
     */
    public void onRecycle(T content)
    {
        this.content = content;
    }

    /**
     * Method to access the root view rendered in the Renderer.
     *
     * @return top view in the view hierarchy of one Renderer.
     */
    public View getRootView()
    {
        return rootView;
    }

    /**
     * @return the content stored in the Renderer.
     */
    protected final T getContent()
    {
        return content;
    }

    /**
     * Configures the content stored in the Renderer.
     */
    protected void setContent(T content)
    {
        this.content = content;
    }

    /**
     * Inflate renderer layout. The view inflated can't be null. If this method returns a null view a
     * NotInflateViewException will be thrown.
     *
     * @param inflater LayoutInflater service to inflate.
     * @return View with the inflated layout.
     */
    protected View inflate(LayoutInflater inflater, ViewGroup parent)
    {
        View inflatedView = inflater.inflate(layoutRes, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    };

    /**
     * Method where the presentation logic algorithm have to be declared or implemented.
     */
    public abstract void render();

    /**
     * Create a clone of the Renderer. This method is the base of the prototype mechanism implemented
     * to avoid create new objects from RendererBuilder. Pay an special attention implementing clone
     * method in Renderer subtypes.
     *
     * @return a copy of the current renderer.
     */
    Renderer copy()
    {
        Renderer copy = null;
        try
        {
            copy = (Renderer) this.clone();
        }
        catch (CloneNotSupportedException e)
        {
            Log.e("Renderer", "All your renderers should be clonables.");
        }
        return copy;
    }
}
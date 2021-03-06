package com.reactnativenavigation.bridge;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.reactnativenavigation.controllers.NavigationCommandsHandler;
import com.reactnativenavigation.params.TitleBarButtonParams;
import com.reactnativenavigation.params.TitleBarLeftButtonParams;
import com.reactnativenavigation.params.parsers.TitleBarButtonParamsParser;
import com.reactnativenavigation.params.parsers.TitleBarLeftButtonParamsParser;

import java.util.List;

/**
 * The basic abstract components we will expose:
 * BottomTabs (app) - boolean
 * TopBar (per screen)
 * - TitleBar
 * - - RightButtons
 * - - LeftButton
 * - TopTabs (segmented control / view pager tabs)
 * DeviceStatusBar (app) (colors are per screen)
 * AndroidNavigationBar (app) (colors are per screen)
 * SideMenu (app) - boolean, (menu icon is screen-based)
 */
public class NavigationReactModule extends ReactContextBaseJavaModule {
    public static final String NAME = "NavigationReactModule";

    public NavigationReactModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void startApp(final ReadableMap params) {
        NavigationCommandsHandler.startApp(BundleConverter.toBundle(params));
    }

    @ReactMethod
    public void setScreenTitleBarTitle(String screenInstanceId, String title) {
        NavigationCommandsHandler.setScreenTitleBarTitle(screenInstanceId, title);
    }

    @ReactMethod
    public void setScreenTitleBarButtons(String screenInstanceId, String navigatorEventId,
                                         ReadableArray rightButtonsParams, ReadableMap leftButtonParams) {
        if (rightButtonsParams != null) {
            setScreenTitleBarRightButtons(screenInstanceId, navigatorEventId, rightButtonsParams);
        }

        if (leftButtonParams != null) {
            setScreenTitleBarLeftButton(screenInstanceId, navigatorEventId, leftButtonParams);
        }
    }

    private void setScreenTitleBarRightButtons(String screenInstanceId, String navigatorEventId, ReadableArray rightButtonsParams) {
        List<TitleBarButtonParams> rightButtons = new TitleBarButtonParamsParser()
                .parseButtons(BundleConverter.toBundle(rightButtonsParams));
        NavigationCommandsHandler.setScreenTitleBarRightButtons(screenInstanceId, navigatorEventId, rightButtons);
    }

    private void setScreenTitleBarLeftButton(String screenInstanceId, String navigatorEventId, ReadableMap leftButtonParams) {
        TitleBarLeftButtonParams leftButton = new TitleBarLeftButtonParamsParser()
                .parseSingleButton(BundleConverter.toBundle(leftButtonParams));
        NavigationCommandsHandler.setScreenTitleBarLeftButtons(screenInstanceId, navigatorEventId, leftButton);
    }

    @ReactMethod
    public void setTabBadge(final ReadableMap params) {
    }

    @ReactMethod
    public void selectBottomTab(final ReadableMap params) {
    }

    @ReactMethod
    public void toggleSideMenuVisible(boolean animated) {
        NavigationCommandsHandler.toggleSideMenuVisible(animated);
    }

    @ReactMethod
    public void setSideMenuVisible(boolean animated, boolean visible) {
        NavigationCommandsHandler.setSideMenuVisible(animated, visible);
    }

    @ReactMethod
    public void toggleTopBarVisible(final ReadableMap params) {
    }

    @ReactMethod
    public void setTopBarVisible(String screenInstanceId, boolean hidden, boolean animated) {
        NavigationCommandsHandler.setTopBarVisible(screenInstanceId, hidden, animated);
    }

    @ReactMethod
    public void toggleBottomTabsVisible(final ReadableMap params) {
    }

    @ReactMethod
    public void setBottomTabsVisible(boolean hidden, boolean animated) {
        NavigationCommandsHandler.setBottomTabsVisible(hidden, animated);
    }

    @ReactMethod
    public void push(final ReadableMap params) {
        NavigationCommandsHandler.push(BundleConverter.toBundle(params));
    }

    @ReactMethod
    public void pop(final ReadableMap params) {
        NavigationCommandsHandler.pop(BundleConverter.toBundle(params));
    }

    @ReactMethod
    public void popToRoot(final ReadableMap params) {
        NavigationCommandsHandler.popToRoot(BundleConverter.toBundle(params));
    }

    @ReactMethod
    public void newStack(final ReadableMap params) {
        NavigationCommandsHandler.newStack(BundleConverter.toBundle(params));
    }

    @ReactMethod
    public void showModal(final ReadableMap params) {
        NavigationCommandsHandler.showModal(BundleConverter.toBundle(params));
    }

    @ReactMethod
    public void dismissAllModals() {
        NavigationCommandsHandler.dismissAllModals();
    }

    @ReactMethod
    public void dismissTopModal() {
        NavigationCommandsHandler.dismissTopModal();
    }
}

<?xml version="1.0" encoding="utf-8"?>
<!--
  Copyright 2019 The Android Open Source Project

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  -->
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:shareInterpolator="false">
    <!-- Fade out, over a black surface, which simulates a black scrim -->
    <alpha
        android:fromAlpha="1"
        android:toAlpha="0.0"
        android:fillEnabled="true"
        android:fillBefore="true"
        android:fillAfter="true"
        android:interpolator="@android:anim/linear_interpolator"
        android:startOffset="35"
        android:duration="50"/>
    <scale
        android:fromXScale="1"
        android:toXScale="1.15"
        android:fromYScale="1"
        android:toYScale="1.15"
        android:pivotX="50%"
        android:pivotY="50%"
        android:fillEnabled="true"
        android:fillBefore="true"
        android:fillAfter="true"
        android:interpolator="@anim/fragment_fast_out_extra_slow_in"
        android:duration="300"/>
</set>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
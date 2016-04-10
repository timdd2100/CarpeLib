# CarpeLib

# GCM Setting Example in .manifests

        <receiver
            android:name="com.google.android.gms.gcm.GcmReceiver"
            android:permission="com.google.android.c2dm.permission.SEND">
            <intent-filter>
                <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
                <action android:name="com.google.android.c2dm.intent.RECEIVE" />
                <category android:name="com.example.project" />
            </intent-filter>
        </receiver>
        
        <service
            android:name="com.example.project.RegistrationIntentService"
            android:exported="false" />
        <service
            android:name="com.example.project.GcmMessageHandler"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.android.c2dm.intent.RECEIVE" />
            </intent-filter>
        </service>
        <service
            android:name="com.example.project.InstanceIDListenerService"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.android.gms.iid.InstanceID" />
            </intent-filter>
        </service>
        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />
            
# GA Setting Example in .manifests

        <meta-data
            android:name="com.google.android.gms.analytics.globalConfigResource"
            android:resource="@xml/global_tracker" />

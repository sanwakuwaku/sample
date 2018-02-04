package com.sanwaku2.memoryreleasesample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    StringStruct mStruct;
    StringStruct mStructStrongRef;
    WeakReference<StringStruct> mStructWeakRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStruct = new StringStruct();
        addObject(mStruct);

        // (A) mStructを強参照
        // mStructStrongRef = mStruct;

        // (B) 弱参照で保持
        mStructWeakRef = new WeakReference<>(mStruct);

        findViewById(R.id.button).setOnClickListener((view) -> {
            startActivity(new Intent(this, AnotherActivity.class));

            // mStructにnullを入れて参照を切ればそのうちGCで解放されるが、
            // mStructStrongRefから強参照されていると解放されない。(A)
            // mStructを参照してるオブジェクトが弱参照のみの場合はmStructの参照を切ればGCで解放される。(B)
            // また、(D)のようにActivityのライフサイクルを超えてActivity参照を保持しなければ、
            // ActivityのfinishでmStructはnullを入れていなくてもGC対象となる。
            mStruct = null;

            finish(); // (C)
        });

        // (D)のようにActivityのライフサイクルを超えて存在するスレッドがActivityの参照を保持していると
        // activityがfinishしてもGCで解放されない。 (C)
        new Thread(() -> {
            Activity activity = MainActivity.this; // (D)
            while (true) { // アプリが死ぬまで生存する。
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void addObject(StringStruct parent) {
        if (StringStruct.sCount > 10000) {
            return;
        }

        StringStruct child = new StringStruct();
        parent.setObject(child);
        addObject(child);
    }

}

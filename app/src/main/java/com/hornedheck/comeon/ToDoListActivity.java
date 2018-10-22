package com.hornedheck.comeon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ToDoListActivity
  extends AppCompatActivity{
	
	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_to_do_list );
		ImageButton settingsButton = findViewById( R.id.settings_button );
		settingsButton.setOnClickListener( new View.OnClickListener(){
			
			@Override
			public void onClick( View v ){
				startActivity( new Intent( ToDoListActivity.this , SettingsActivity.class ) );
			}
		} );
	}
}

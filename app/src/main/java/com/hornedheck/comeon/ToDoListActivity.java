package com.hornedheck.comeon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ToDoListActivity
  extends AppCompatActivity{
	
	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_to_do_list );
	}
}

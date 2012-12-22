package info.curtbinder.reefangel.widget;

import info.curtbinder.reefangel.phone.R;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;

public class PhoneBanner4x1_1 extends BaseWidgetReceiver {

	@Override
	protected void updateDisplay ( Context context ) {
		RemoteViews updateViews =
				new RemoteViews( context.getPackageName(),
					R.layout.widgetphonebanner4x1_1 );

		String date, t1, t2, t3, dp, ap, ph;
		date = t1 = t2 = t3 = ap = dp = ph = "";
		try {
			Uri CONTENT_URI = Uri.parse( BaseWidgetReceiver.URI_LATEST_STRING );
			ContentResolver cr = context.getContentResolver();
			Cursor c = cr.query( CONTENT_URI, null, null, null, null );
			// move the cursor to the first item in the list
			if ( c.moveToFirst() ) {
				// work with the data
				date = c.getString( c.getColumnIndex( "logdate" ) );
				t1 = c.getString( c.getColumnIndex( "t1" ) );
				t2 = c.getString( c.getColumnIndex( "t2" ) );
				t3 = c.getString( c.getColumnIndex( "t3" ) );
			}
			// close the cursor from the database
			c.close();
		} catch ( Exception e ) {
			e.printStackTrace();
		}

		updateViews.setTextViewText( R.id.widget_date, date );
		updateViews.setTextViewText( R.id.widget_t1, t1 );
		updateViews.setTextViewText( R.id.widget_t2, t2 );
		updateViews.setTextViewText( R.id.widget_t3, t3 );

		// get the Component Name to identify the widget to update
		ComponentName widgetComponentName =
				new ComponentName( context, PhoneBanner4x1_1.class );
		// get the global AppWidgetManager
		AppWidgetManager manager = AppWidgetManager.getInstance( context );

		// update the Weather AppWdiget
		manager.updateAppWidget( widgetComponentName, updateViews );
	}

}

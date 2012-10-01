package eu.ttbox.geoping.domain.smslog;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import eu.ttbox.geoping.domain.model.SmsLog;
import eu.ttbox.geoping.domain.model.SmsLogTypeEnum;
import eu.ttbox.geoping.domain.smslog.SmsLogDatabase.SmsLogColumns;
import eu.ttbox.geoping.service.encoder.GeoPingMessage;
import eu.ttbox.geoping.service.encoder.SmsMessageActionEnum;

public class SmsLogHelper {

	boolean isNotInit = true;
	public int idIdx = -1;
	public int timeIdx = -1;
	public int actionIdx = -1;
	public int messageIdx = -1;
	public int phoneIdx = -1;
	public int phoneNormalizedIdx = -1;
	public int smsLogTypeIdx = -1;

	public SmsLogHelper initWrapper(Cursor cursor) {
		idIdx = cursor.getColumnIndex(SmsLogColumns.COL_ID);
		timeIdx = cursor.getColumnIndex(SmsLogColumns.COL_TIME);
		actionIdx = cursor.getColumnIndex(SmsLogColumns.COL_ACTION);
		phoneIdx = cursor.getColumnIndex(SmsLogColumns.COL_PHONE);
		phoneNormalizedIdx = cursor.getColumnIndex(SmsLogColumns.COL_PHONE_NORMALIZED);
		smsLogTypeIdx = cursor.getColumnIndex(SmsLogColumns.COL_SMSLOG_TYPE);
		messageIdx = cursor.getColumnIndex(SmsLogColumns.COL_MESSAGE);
		isNotInit = false;
		return this;
	}

	public SmsLog getEntity(Cursor cursor) {
		if (isNotInit) {
			initWrapper(cursor);
		}
		SmsLog user = new SmsLog();
		user.setId(idIdx > -1 ? cursor.getLong(idIdx) : -1);
		user.setTime(timeIdx > -1 ? cursor.getLong(timeIdx) : SmsLog.UNSET_TIME);
		user.setAction(actionIdx > -1 ? cursor.getString(actionIdx) : null);
		user.setPhone(phoneIdx > -1 ? cursor.getString(phoneIdx) : null);
		user.setSmsLogType(smsLogTypeIdx > -1 ? getSmsLogType(cursor ) : null);
		user.setMessage(messageIdx > -1 ? cursor.getString(messageIdx) : null);
		return user;
	}

	private SmsLogHelper setTextWithIdx(TextView view, Cursor cursor, int idx) {
		view.setText(cursor.getString(idx));
		return this;
	}

	public SmsLogHelper setTextSmsLogId(TextView view, Cursor cursor) {
		return setTextWithIdx(view, cursor, idIdx);
	}

	public String getSmsLogIdAsString(Cursor cursor) {
		return cursor.getString(idIdx);
	}

	public long getSmsLogId(Cursor cursor) {
		return cursor.getLong(idIdx);
	}

	
	public SmsLogHelper setTextSmsLogAction(TextView view, Cursor cursor) { 
		return setTextWithIdx(view, cursor, actionIdx); 
	}
	
	public SmsLogHelper setTextSmsLogMessage(TextView view, Cursor cursor) {
		return setTextWithIdx(view, cursor, messageIdx);
	}

	public SmsLogHelper setTextSmsLogPhone(TextView view, Cursor cursor) {
		return setTextWithIdx(view, cursor, phoneIdx);
	}

	public SmsLogHelper setTextSmsLogTime(TextView view, Cursor cursor) {
		return setTextWithIdx(view, cursor, timeIdx);
	}
	
	public String getSmsLogPhone(Cursor cursor) {
		return cursor.getString(phoneIdx);
	}
	
	
	
	public SmsLogTypeEnum getSmsLogType(Cursor cursor) {
		return  SmsLogTypeEnum.getByCode(cursor.getInt(smsLogTypeIdx));
	}

	public static ContentValues getContentValues(SmsLog vo) {
		ContentValues initialValues = new ContentValues();
		if (vo.id > -1) {
			initialValues.put(SmsLogColumns.COL_ID, Long.valueOf(vo.id));
		}
		initialValues.put(SmsLogColumns.COL_TIME, vo.time);
		initialValues.put(SmsLogColumns.COL_PHONE, vo.phone);
		initialValues.put(SmsLogColumns.COL_ACTION, vo.action);
		initialValues.put(SmsLogColumns.COL_MESSAGE, vo.message);
		initialValues.put(SmsLogColumns.COL_SMSLOG_TYPE, vo.smsLogType.getCode());
		return initialValues;
	}

	public static ContentValues getContentValues(SmsLogTypeEnum type, GeoPingMessage geoMessage) {
		return getContentValues(type, geoMessage.phone, geoMessage.action, geoMessage.params);
	}

	public static ContentValues getContentValues(SmsLogTypeEnum type, String phone, SmsMessageActionEnum action, Bundle params) {
		ContentValues values = new ContentValues();
		values.put(SmsLogColumns.COL_TIME, System.currentTimeMillis());
		values.put(SmsLogColumns.COL_PHONE, phone);
		values.put(SmsLogColumns.COL_ACTION, action.intentAction);
		values.put(SmsLogColumns.COL_SMSLOG_TYPE, type.getCode());
		if (params != null && !params.isEmpty()) {
			// TODO Message form Bundle
		}
		return values;
	}



}
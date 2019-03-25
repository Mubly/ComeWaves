package com.mubly.comewaves.view.interfaceview;

import android.view.View;

public interface AsymmetricView {
  boolean isDebugging();
  int getNumColumns();
  boolean isAllowReordering();
  void fireOnItemClick(int index, View v);
  boolean fireOnItemLongClick(int index, View v);
  int getColumnWidth();
  int getDividerHeight();
  int getRequestedHorizontalSpacing();
}

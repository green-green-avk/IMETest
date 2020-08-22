package green_green_avk.imetest;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;

import androidx.annotation.NonNull;

public final class InputView extends View {

    public interface Output {
        void print(@NonNull String v);
    }

    public Output output = null;
    public boolean cookedIME = false;

    public InputView(final Context context) {
        super(context);
        init();
    }

    public InputView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InputView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setFocusableInTouchMode(true);
        requestFocus();
    }

    private void print(@NonNull final String v) {
        if (output != null) output.print(v);
    }

    private void printStackTrace(final Object... args) {
        print("===\n");
        for (final Object a : args) {
            print(a.getClass().getName());
            print("\n");
            print(a.toString());
            print("\n");
        }
        print("---\n");
        boolean found = false;
        for (final StackTraceElement v : Thread.currentThread().getStackTrace()) {
            if ("printStackTrace".equals(v.getMethodName())) {
                found = true;
                continue;
            }
            if (!found) continue;
            print(v.toString());
            print("\n");
        }
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return true;
    }

    @Override
    public boolean onKeyPreIme(final int keyCode, final KeyEvent event) {
        printStackTrace(keyCode, event);
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public InputConnection onCreateInputConnection(final EditorInfo outAttrs) {
        print("\n=====\nUsing " + (cookedIME ? "cooked" : "raw") + " mode\n=====\n");
        outAttrs.actionLabel = null;
        outAttrs.inputType = cookedIME ? EditorInfo.TYPE_CLASS_TEXT : EditorInfo.TYPE_NULL;
        outAttrs.imeOptions = EditorInfo.IME_ACTION_NONE | EditorInfo.IME_FLAG_NO_FULLSCREEN |
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ?
                        EditorInfo.IME_FLAG_NO_PERSONALIZED_LEARNING : 0);
        return new BaseInputConnection(this, false) {
            @Override
            public boolean beginBatchEdit() {
                printStackTrace();
                return super.beginBatchEdit();
            }

            @Override
            public boolean endBatchEdit() {
                printStackTrace();
                return super.endBatchEdit();
            }

            @Override
            public void closeConnection() {
                printStackTrace();
                super.closeConnection();
            }

            @Override
            public boolean clearMetaKeyStates(final int states) {
                printStackTrace(states);
                return super.clearMetaKeyStates(states);
            }

            @Override
            public boolean commitCompletion(final CompletionInfo text) {
                printStackTrace(text);
                return super.commitCompletion(text);
            }

            @Override
            public boolean commitCorrection(final CorrectionInfo correctionInfo) {
                printStackTrace(correctionInfo);
                return super.commitCorrection(correctionInfo);
            }

            @Override
            public boolean commitText(final CharSequence text, final int newCursorPosition) {
                printStackTrace(text, newCursorPosition);
                return super.commitText(text, newCursorPosition);
            }

            @Override
            public boolean deleteSurroundingText(final int beforeLength, final int afterLength) {
                printStackTrace(beforeLength, afterLength);
                return super.deleteSurroundingText(beforeLength, afterLength);
            }

            @Override
            public boolean deleteSurroundingTextInCodePoints(final int beforeLength,
                                                             final int afterLength) {
                printStackTrace(beforeLength, afterLength);
                return super.deleteSurroundingTextInCodePoints(beforeLength, afterLength);
            }

            @Override
            public boolean finishComposingText() {
                printStackTrace();
                return super.finishComposingText();
            }

            @Override
            public int getCursorCapsMode(final int reqModes) {
                printStackTrace(reqModes);
                return super.getCursorCapsMode(reqModes);
            }

            @Override
            public ExtractedText getExtractedText(final ExtractedTextRequest request,
                                                  final int flags) {
                printStackTrace(request, flags);
                return super.getExtractedText(request, flags);
            }

            @Override
            public CharSequence getTextBeforeCursor(final int length, final int flags) {
                printStackTrace(length, flags);
                return super.getTextBeforeCursor(length, flags);
            }

            @Override
            public CharSequence getSelectedText(final int flags) {
                printStackTrace(flags);
                return super.getSelectedText(flags);
            }

            @Override
            public CharSequence getTextAfterCursor(final int length, final int flags) {
                printStackTrace(length, flags);
                return super.getTextAfterCursor(length, flags);
            }

            @Override
            public boolean performEditorAction(final int actionCode) {
                printStackTrace(actionCode);
                return super.performEditorAction(actionCode);
            }

            @Override
            public boolean performContextMenuAction(final int id) {
                printStackTrace(id);
                return super.performContextMenuAction(id);
            }

            @Override
            public boolean performPrivateCommand(final String action, final Bundle data) {
                printStackTrace(action, data);
                return super.performPrivateCommand(action, data);
            }

            @Override
            public boolean requestCursorUpdates(final int cursorUpdateMode) {
                printStackTrace(cursorUpdateMode);
                return super.requestCursorUpdates(cursorUpdateMode);
            }

            @Override
            public boolean setComposingText(final CharSequence text, final int newCursorPosition) {
                printStackTrace(text, newCursorPosition);
                return super.setComposingText(text, newCursorPosition);
            }

            @Override
            public boolean setComposingRegion(final int start, final int end) {
                printStackTrace(start, end);
                return super.setComposingRegion(start, end);
            }

            @Override
            public boolean setSelection(final int start, final int end) {
                printStackTrace(start, end);
                return super.setSelection(start, end);
            }

            @Override
            public boolean sendKeyEvent(final KeyEvent event) {
                printStackTrace(event);
                return super.sendKeyEvent(event);
            }

            @Override
            public boolean reportFullscreenMode(final boolean enabled) {
                printStackTrace(enabled);
                return super.reportFullscreenMode(enabled);
            }

            @Override
            public boolean commitContent(final InputContentInfo inputContentInfo, final int flags,
                                         final Bundle opts) {
                printStackTrace(inputContentInfo, flags, opts);
                return super.commitContent(inputContentInfo, flags, opts);
            }
        };
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        printStackTrace(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(final int keyCode, final KeyEvent event) {
        printStackTrace(keyCode, event);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(final int keyCode, final KeyEvent event) {
        printStackTrace(keyCode, event);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(final int keyCode, final int repeatCount, final KeyEvent event) {
        printStackTrace(keyCode, repeatCount, event);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }
}

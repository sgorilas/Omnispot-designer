/**
 * Eclipse GEF redbook sample application
 * $Source: /usr/local/cvsroot/SAL330RGEFDemoApplication/src/com/ibm/itso/sal330r/gefdemo/editor/DelegatingCommandStack.java,v $
 * $Revision: 1.2 $
 * 
 * (c) Copyright IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Gunnar Wagenknecht - initial contribution
 * 
 */
package com.kesdip.designer.editor;

import java.util.EventObject;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.UnexecutableCommand;

/**
 * This is a delegating command stack, which delegates everything
 * to another CommandStack except event listners.
 * 
 * <p>Event listeners registered to a <code>DelegatingCommandStack</code>
 * will be informed whenever the underlying <code>CommandStack</code>
 * changes. They will not be registered to the underlying
 * <code>CommandStack</code> directly but they will be informed 
 * about change events of them.
 * 
 * @author Gunnar Wagenknecht
 */
public class DelegatingCommandStack
    extends CommandStack
    implements CommandStackListener
{
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};
    /** the current command stack */
    private CommandStack currentCommandStack;

    /**
     * Returns the current <code>CommandStack</code>.
     * @return the current <code>CommandStack</code>
     */
    public CommandStack getCurrentCommandStack()
    {
        return currentCommandStack;
    }

    /**
     * Sets the current <code>CommandStack</code>.
     * @param stack the <code>CommandStack</code> to set
     */
    @SuppressWarnings("deprecation")
	public void setCurrentCommandStack(CommandStack stack)
    {
        if (currentCommandStack == stack)
            return;

        // remove from old command stack
        if (null != currentCommandStack)
            currentCommandStack.removeCommandStackListener(this);

        // set new command stack
        currentCommandStack = stack;

        // watch new command stack
        currentCommandStack.addCommandStackListener(this);

        // the command stack changed
        notifyListeners();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#canRedo()
     */
    public boolean canRedo()
    {
        if (null == currentCommandStack)
            return false;

        return currentCommandStack.canRedo();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#canUndo()
     */
    public boolean canUndo()
    {
        if (null == currentCommandStack)
            return false;

        return currentCommandStack.canUndo();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#dispose()
     */
    public void dispose()
    {
        if (null != currentCommandStack)
            currentCommandStack.dispose();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#execute(org.eclipse.gef.commands.Command)
     */
    public void execute(Command command)
    {
        if (null != currentCommandStack)
            currentCommandStack.execute(command);
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#flush()
     */
    public void flush()
    {
        if (null != currentCommandStack)
            currentCommandStack.flush();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#getCommands()
     */
    public Object[] getCommands()
    {
        if (null == currentCommandStack)
            return EMPTY_OBJECT_ARRAY;

        return currentCommandStack.getCommands();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#getRedoCommand()
     */
    public Command getRedoCommand()
    {
        if (null == currentCommandStack)
            return UnexecutableCommand.INSTANCE;

        return currentCommandStack.getRedoCommand();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#getUndoCommand()
     */
    public Command getUndoCommand()
    {
        if (null == currentCommandStack)
            return UnexecutableCommand.INSTANCE;

        return currentCommandStack.getUndoCommand();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#getUndoLimit()
     */
    public int getUndoLimit()
    {
        if (null == currentCommandStack)
            return -1;

        return currentCommandStack.getUndoLimit();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#isDirty()
     */
    public boolean isDirty()
    {
        if (null == currentCommandStack)
            return false;

        return currentCommandStack.isDirty();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#markSaveLocation()
     */
    public void markSaveLocation()
    {
        if (null != currentCommandStack)
            currentCommandStack.markSaveLocation();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#redo()
     */
    public void redo()
    {
        if (null != currentCommandStack)
            currentCommandStack.redo();
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#setUndoLimit(int)
     */
    public void setUndoLimit(int undoLimit)
    {
        if (null != currentCommandStack)
            currentCommandStack.setUndoLimit(undoLimit);
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStack#undo()
     */
    public void undo()
    {
        if (null != currentCommandStack)
            currentCommandStack.undo();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "DelegatingCommandStack(" + currentCommandStack + ")";
    }

    /* (non-Javadoc)
     * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
     */
    @SuppressWarnings("deprecation")
	public void commandStackChanged(EventObject event)
    {
        notifyListeners();
    }
}

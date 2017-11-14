package eu.epitech.Model.Entity;

import java.util.List;

public interface IArea
{
	void			setActionName(String actionName);
	void			setActionArgs(String[] actionArgs);
	void			setReactionName(String reactionName);
	void			setReactionArgs(String[] reactionArgs);
	void			setTmpData(String tmpData);
	void			setUserId(int userId);
	void			setId(int Id);
	String			getActionName();
	String[]		getActionArgs();
	String			getReactionName();
	String[]		getReactionArgs();
	String			getTmpData();
	int				getUserId();
	int				getId();
}

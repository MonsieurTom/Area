package eu.epitech.Model.Entity;

import eu.epitech.AreaDao;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Area implements IArea
{
    private String      actionName;
    private String[]    actionArgs;
    private String      reactionName;
    private String[]    reactionArgs;
    private String      tmpData;
    private int         id;
    private int         userId;

    public Area()
    { }

    public Area(String actionName, String[] actionArgs, String reactionName, String[] reactionArgs, String tmpData,
         int id, int userId)
    {
        this.actionArgs = actionArgs;
        this.actionName = actionName;
        this.id =  id;
        this.reactionArgs = reactionArgs;
        this.reactionName = reactionName;
        this.userId = userId;
        this.tmpData = tmpData;
    }

    @Override
    public void setActionArgs(String[] actionArgs) {
        this.actionArgs = actionArgs;
    }

    @Override
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public void setReactionArgs(String[] reactionArgs) {
        this.reactionArgs = reactionArgs;
    }

    @Override
    public void setReactionName(String reactionName) {
        this.reactionName = reactionName;
    }

    @Override
    public void setTmpData(String tmpData) {
        this.tmpData = tmpData;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String[] getActionArgs() {
        return actionArgs;
    }

    @Override
    public String getActionName() {
        return actionName;
    }

    @Override
    public String[] getReactionArgs() {
        return reactionArgs;
    }

    @Override
    public String getReactionName() {
        return reactionName;
    }

    @Override
    public String getTmpData() {
        return tmpData;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getUserId() {
        return userId;
    }
}

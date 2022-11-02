package org.group11.Packages.Game.Scripts.Cameras;

import org.group11.Packages.Core.DataStructures.Vector2;
import org.group11.Packages.Core.DataStructures.Vector3;
import org.group11.Packages.Core.Main.Camera;
import org.group11.Packages.Core.Main.GameObject;

/**
 *
 */
public class FollowingCamera extends Camera {
    private GameObject _subject;

    private Vector3 targetPos = new Vector3();

    public FollowingCamera(GameObject subject){
        this.transform.position = new Vector3(0,0,-5);
        this._subject = subject;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        if(_subject == null) return;
        targetPos = _subject.transform.position;
        Vector3 thisPos = this.transform.position;
        float distance = (float)Math.sqrt(
                (targetPos.x - thisPos.x)*(targetPos.x - thisPos.x) +
                        (targetPos.y - thisPos.y)*(targetPos.y - thisPos.y) +
                        (targetPos.z - thisPos.z)*(targetPos.z - thisPos.z));
        if(distance > 0.1){
            float mag = (float)Math.sqrt((targetPos.x - thisPos.x)*(targetPos.x - thisPos.x)+(targetPos.y - thisPos.y)/(targetPos.y - thisPos.y));
            Vector2 moveDir = new Vector2((targetPos.x - thisPos.x)/mag, (targetPos.y - thisPos.y)/mag);
            Vector3 newPosition = new Vector3(this.transform.position.x+moveDir.x/10, this.transform.position.y+moveDir.y/10, this.transform.position.z);
            this.transform.setPosition(newPosition);
        }
    }

    @Override
    public void onKeyDown(int key) {
        if (key == 'Z') {
            this.transform.position.z += -0.2;
        } else if (key == 'X') {
            this.transform.position.z += +0.2;
        }
        if (key == 'U') {
            this._rot.x += 0.05;
        } else if (key == 'I') {
            this._rot.x -= 0.05;
        }
        if (key == 'J') {
            this._rot.y += 0.05;
        } else if (key == 'K') {
            this._rot.y -= 0.05;
        }
    }
}

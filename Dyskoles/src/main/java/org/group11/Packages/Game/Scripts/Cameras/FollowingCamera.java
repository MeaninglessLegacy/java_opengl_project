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
        this.transform.position = new Vector3(0,0,-8);
        this.transform.rotation = new Vector3(-2.6f,0f,0f);
        this._subject = subject;
    }

    //******************************************************************************************************************
    //* overrides
    //******************************************************************************************************************
    @Override
    public void update() {
        if(_subject == null) return;
        targetPos.x = _subject.transform.position.x;
        targetPos.y = _subject.transform.position.y-3;
        targetPos.z = _subject.transform.position.z;
        Vector3 thisPos = this.transform.position;
        float distance = (float)Math.sqrt(
                (targetPos.x - thisPos.x)*(targetPos.x - thisPos.x) +
                        (targetPos.y - thisPos.y)*(targetPos.y - thisPos.y));
        if(distance > 0.1){
            float mag = (float)Math.sqrt((targetPos.x - thisPos.x)*(targetPos.x - thisPos.x)+(targetPos.y - thisPos.y)*(targetPos.y - thisPos.y));
            Vector2 moveDir = new Vector2((targetPos.x - thisPos.x)/mag, (targetPos.y - thisPos.y)/mag);
            Vector3 newPosition = new Vector3((float) (this.transform.position.x+moveDir.x*0.1f*Math.abs(Math.sin(mag))), (float) (this.transform.position.y+moveDir.y*0.1f*Math.abs(Math.sin(mag))), this.transform.position.z);
            this.transform.setPosition(newPosition);
        }
    }

    @Override
    public void onKeyDown(int key) {
        if (key == 'R') {
            this.transform.position.x += -0.2;
        } else if (key == 'F') {
            this.transform.position.x += +0.2;
        }
        if (key == 'T') {
            this.transform.position.y += -0.2;
        } else if (key == 'G') {
            this.transform.position.y += +0.2;
        }
        if (key == 'Y') {
            this.transform.position.z += -0.2;
        } else if (key == 'H') {
            this.transform.position.z += +0.2;
        }
        if (key == 'I') {
            this.transform.rotation.x += -0.1;
        } else if (key == 'K') {
            this.transform.rotation.x += +0.1;
        }
        if (key == 'O') {
            this.transform.rotation.y += -0.1;
        } else if (key == 'L') {
            this.transform.rotation.y += +0.1;
        }

        //System.out.println("rotation: "+transform.rotation.x+","+transform.rotation.y);
        //System.out.println("position: "+transform.position.x+","+transform.position.y+","+transform.position.z);
    }
}

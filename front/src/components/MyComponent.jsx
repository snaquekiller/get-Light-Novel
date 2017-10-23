import React from 'react';
import { observer } from 'mobx-react';

function MyComponent() {
  return (
    <div>
      <button type="button">Click me!</button>
      <h4>You've clicked the button  times.</h4>
      <h5>You've clicked button an  number of times.</h5>
    </div>
  );
}

MyComponent.propTypes = {
};

export default observer(MyComponent);

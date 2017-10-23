import React from 'react';
import { observer } from 'mobx-react';
import MyComponent from 'components/MyComponent';

@observer(['store'])
export default class Home extends React.Component {
  render() {
    const { name, description } = this.props.store;

    return (
      <div>
        <h2>Welcome to the {name} project.</h2>
        <h3>This project is {description}.</h3>
        <MyComponent store={this.props.store} />
      </div>
    );
  }
}

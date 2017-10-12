import React from 'react';
import MobxDevTools from 'mobx-react-devtools';
import { Router, browserHistory } from 'react-router';

export default class App extends React.Component {
  render() {
    return (
      <div>
        <Router history={browserHistory} routes={this.props.routes} />
        <MobxDevTools />
      </div>
    );
  }
}

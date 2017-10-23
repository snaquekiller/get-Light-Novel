import React from 'react';
import ReactDOM from 'react-dom';
import MobxDevTools from 'mobx-react-devtools';
import MyComponent from 'components/MyComponent';
import { Router, Route, hashHistory, Link } from 'react-router';
// fix safari 9 browser incompatibility for intl object
import 'libs/intl';

const App = () => (
  <ul>
    {/* <Link to="/MyComponent">MyComponent</Link> */}
    <li>coucou</li>
  </ul>
);


ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={App} />
    <Route path="/MyComponent" component={MyComponent} />
  </Router>,
  <MobxDevTools />,
  document.getElementById('root')
);

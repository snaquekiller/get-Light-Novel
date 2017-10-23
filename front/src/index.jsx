import React from 'react';
import ReactDOM from 'react-dom';
import MobxDevTools from 'mobx-react-devtools';
import MyComponent from 'components/MyComponent';
import { Route, BrowserRouter, Link, Switch } from 'react-router-dom';
// fix safari 9 browser incompatibility for intl object
import 'libs/intl';

/* eslint anchor-is-valid: 0 */
const App = () => (
  <ul>
    <Link to="/test">MyComponent</Link>
    <li>coucou</li>
  </ul>
);

ReactDOM.render(
  <div>
    <BrowserRouter >
      <div>
        <Switch>
          <Route exact path="/test" component={MyComponent} />
          <Route path="/" component={App} />
        </Switch>
      </div>
    </BrowserRouter>
    <MobxDevTools />
  </div>,
  document.getElementById('root')
);

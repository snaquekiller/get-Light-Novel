import React from 'react';
import { Route, IndexRoute, Redirect } from 'react-router';
import Layout from 'views/Layout';
import Home from 'views/Home';

export default (store) => {
  function authenticate(nextState, replaceState) {
    // TODO
  }

  return (
    <Route component={Layout} path="/">
      <IndexRoute onEnter={authenticate} component={Home} />
      <Redirect from="*" to="/404" />
    </Route>
  );
};

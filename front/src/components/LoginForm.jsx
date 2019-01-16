import React from "react";
import styled from "styled-components";
import Button from "react-bootstrap/lib/Button";
import FormGroup from "react-bootstrap/lib/FormGroup";
import Form from "react-bootstrap/lib/Form";
import FormControl from "react-bootstrap/lib/FormControl";
import ControlLabel from "react-bootstrap/lib/ControlLabel";
import Panel from "react-bootstrap/lib/Panel";

import LoginService from "../service/LoginService.jsx";

const PanelForm = styled.div`
  width: 400px;
  margin-left: auto;
  margin-right: auto;
`;
export default class LoginForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      manga: undefined,
      login: "",
      password: "",
      showModal: false
    };
    this.login = this.login.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
    this.handlePassword = this.handlePassword.bind(this);
  }

  handleLogin(e) {
    this.setState({ login: e.target.value });
  }

  handlePassword(e) {
    this.setState({ password: e.target.value });
  }

  login() {
    LoginService.login(this.state.login, this.state.password).then(mangaGet => {
      console.log("manga", mangaGet);
    });
  }

  render() {
    return (
      <PanelForm>
        <Panel>
          <Form>
            <FormGroup controlId="formBasicEmail">
              <ControlLabel>Login</ControlLabel>
              <FormControl
                type="email"
                placeholder="Email"
                onChange={this.handleLogin}
                value={this.state.login}
              />
              <FormControl
                type="password"
                placeholder="Password"
                onChange={this.handlePassword}
                value={this.state.password}
              />
              <FormControl.Feedback />
            </FormGroup>
            <Button onClick={() => this.login()} type="button">
              Login
            </Button>
          </Form>
        </Panel>
      </PanelForm>
    );
  }
}

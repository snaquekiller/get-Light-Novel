import React from "react";
import Panel from "react-bootstrap/lib/Panel";
import Image from "react-bootstrap/lib/Image";
import ListGroupItem from "react-bootstrap/lib/ListGroupItem";
import ListGroup from "react-bootstrap/lib/ListGroup";
import styled from "styled-components";

const MangaDiv = styled.div`
  width: 400px;
  img {
    max-width: 200px;
    margin-left: auto;
    margin-right: auto;
  }

  .mangaName {
    font-weight: bold;
    font-size: 20px;
    mcolor: #050c84;
  }
  .title {
    font-weight: bold;
    mcolor: #050c84;
  }

  .text {
    font-weight: normal;
    color: #333;
  }
  .list-group {
    max-width: 150px;
    margin-left: auto;
    margin-right: auto;
    img {
      padding: 5px 15px;
    }
  }
`;

export default class Manga extends React.Component {
  render() {
    return (
      <MangaDiv>
        <Panel>
          <Image src={this.state.manga.image} responsive />
          <div className="mangaName">{this.state.manga.name} </div>
          <span className="title">Author:</span>
          <span className="text"> {this.state.manga.author}</span>
          <ListGroup>
            {!this.state.manga.chapters
              ? ""
              : this.state.manga.chapters.map(url => {
                  let chapterNum = url.split("/");
                  chapterNum = chapterNum[chapterNum.length - 1];
                  return (
                    <ListGroupItem href={url} active key={chapterNum}>
                      Chapter {chapterNum}
                    </ListGroupItem>
                  );
                })}
          </ListGroup>
        </Panel>
      </MangaDiv>
    );
  }
}

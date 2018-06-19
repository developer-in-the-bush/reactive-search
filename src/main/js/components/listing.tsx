import * as React from "react";
import {TokensDocument} from "../model";

export interface HelloProps {
    elements: TokensDocument[],
    title: string
}

export class Listing extends React.PureComponent<HelloProps, {}> {

    render() {
        const documents = this.props.elements.map(document => (
            <div key={document.id} style={{"border": "1px solid rgba(0, 0, 0, .5)"}}><h4><p>Id: {document.id}</p><p>Tokens: {document.tokens}</p></h4></div>));
        return <div>
            <h2>{this.props.title}</h2>
            {documents.map(entry => entry)}
        </div>
    }
}
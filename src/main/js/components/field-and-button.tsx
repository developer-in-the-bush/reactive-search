import * as React from "react";
import {Button, TextField} from "@material-ui/core";

export interface FormInputButtonProps {
    buttonName: string;
    buttonClickHandler?: (content: string) => void;
    label?: string;
}

export interface FormInputButtonState {
    inputValue: string;
}

export default class FormInputButton extends React.Component<FormInputButtonProps, FormInputButtonState> {

    constructor(props: FormInputButtonProps) {
        super(props);
        this.state = {
            inputValue: ""
        }
    }

    render() {
        return <div id="input-button"><TextField label={this.props.label}
                                                 onChange={(event) => {
                                                     this.setState({
                                                         inputValue: event.target.value
                                                     });
                                                 }
                                                 }/>
            <Button disabled={!(this.state.inputValue && this.state.inputValue.length > 0)} onClick={() => {
                this.props.buttonClickHandler(this.state.inputValue);
            }
            }>{this.props.buttonName}</Button></div>;
    }
}
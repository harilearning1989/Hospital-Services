export class Utils {

  static omitSpecialChars(event: KeyboardEvent) {
    let regex = new RegExp("^[a-zA-Z0-9 ]+$");
    let str = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (event.which === 32) {
      return false;
    }
    if (regex.test(str)) {
      return true;
    }
    event.preventDefault();
    return false;
  }

  static omitSpecialCharsAndNumbers(event: KeyboardEvent) {
    //let regex = new RegExp("^[a-zA-Z0-9 ]+$");
    let regex = new RegExp("^[a-zA-Z ]+$");
    let str = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (event.which === 32) {
      return false;
    }
    if (regex.test(str)) {
      return true;
    }
    event.preventDefault();
    return false;
  }

  static allowOnlyNumbers(event: KeyboardEvent) {
    let regex = new RegExp("^[0-9]+$");
    let str = String.fromCharCode(!event.charCode ? event.which : event.charCode);
    if (event.which === 32) {
      return false;
    }
    if (regex.test(str)) {
      return true;
    }
    event.preventDefault();
    return false;
  }

  static omitSpaces(event: KeyboardEvent) {
    if (event.which === 32) {
      return false;
    }
    return true;
  }
}

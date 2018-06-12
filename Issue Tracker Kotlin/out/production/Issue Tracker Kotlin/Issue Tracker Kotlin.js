if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Issue Tracker Kotlin'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Issue Tracker Kotlin'.");
}
this['Issue Tracker Kotlin'] = function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var throwCCE = Kotlin.throwCCE;
  var Unit = Kotlin.kotlin.Unit;
  function main$lambda(it) {
    submit();
    println('button pressed');
    return Unit;
  }
  function main$lambda_0(it) {
    println('button pressed two');
    return Unit;
  }
  function main(args) {
    var tmp$, tmp$_0;
    println('everything ok!');
    var submitButton = Kotlin.isType(tmp$ = document.getElementById('submit-btn'), HTMLButtonElement) ? tmp$ : throwCCE();
    submitButton.addEventListener('click', main$lambda);
    var materialButton = Kotlin.isType(tmp$_0 = document.getElementById('button'), HTMLButtonElement) ? tmp$_0 : throwCCE();
    materialButton.addEventListener('click', main$lambda_0);
  }
  function submit() {
    var tmp$;
    var unorderedList = Kotlin.isType(tmp$ = document.getElementById('issue-posted'), HTMLUListElement) ? tmp$ : throwCCE();
    var listItem = document.createElement('li');
    listItem.innerHTML = 'created listItem';
    unorderedList.appendChild(listItem);
  }
  function doSomething() {
  }
  _.main_kand9s$ = main;
  _.submit = submit;
  _.doSomething = doSomething;
  main([]);
  Kotlin.defineModule('Issue Tracker Kotlin', _);
  return _;
}(typeof this['Issue Tracker Kotlin'] === 'undefined' ? {} : this['Issue Tracker Kotlin'], kotlin);

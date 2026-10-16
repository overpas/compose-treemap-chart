;(function (config) {
    const fs = require('fs');
    const path = require('path');
    const skikoFile = path.join(config.basePath, 'kotlin', 'skiko.mjs');
    if (!fs.existsSync(skikoFile)) {
        return;
    }
    const awaitSkikoFile = path.join(config.basePath, 'static', 'await-skiko.mjs');
    fs.writeFileSync(
        awaitSkikoFile,
        'import { awaitSkiko } from "../kotlin/skiko.mjs";\n' +
        'before(function () {\n' +
        '    this.timeout(60000);\n' +
        '    return awaitSkiko;\n' +
        '});\n',
    );
    config.files.unshift(awaitSkikoFile);
    config.preprocessors[awaitSkikoFile] = ['webpack', 'sourcemap'];
})(config);

config.set({
    browserDisconnectTimeout: 60000,
    browserNoActivityTimeout: 120000,
    pingTimeout: 60000,
});
config.client = config.client || {};
config.client.mocha = config.client.mocha || {};
config.client.mocha.timeout = 60000;

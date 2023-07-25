# capacitor-plugin-esptouch

A capacitor JS plugin to use ESPtouch V2 in your capacitor application.

Only android is supported for the moment.

Check example for usage.

## Install

```bash
npm install capacitor-plugin-esptouch
npx cap sync
```

## API

<docgen-index>

* [`EsptouchV2(...)`](#esptouchv2)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### EsptouchV2(...)

```typescript
EsptouchV2(options: EsptouchV2Request) => Promise<EsptouchV2Response>
```

| Param         | Type                                                            |
| ------------- | --------------------------------------------------------------- |
| **`options`** | <code><a href="#esptouchv2request">EsptouchV2Request</a></code> |

**Returns:** <code>Promise&lt;<a href="#esptouchv2response">EsptouchV2Response</a>&gt;</code>

--------------------


### Interfaces


#### EsptouchV2Response

| Prop           | Type                |
| -------------- | ------------------- |
| **`response`** | <code>string</code> |
| **`error`**    | <code>string</code> |


#### EsptouchV2Request

| Prop              | Type                |
| ----------------- | ------------------- |
| **`ssid`**        | <code>string</code> |
| **`bssid`**       | <code>string</code> |
| **`password`**    | <code>string</code> |
| **`custom_data`** | <code>string</code> |

</docgen-api>

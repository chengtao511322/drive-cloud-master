import request from '@/utils/request'


// 查询列表
export function listCouponProductRelation(query) {
  return request({
    url: '/marketing/couponProductRelation/pageList',
    method: 'get',
    params: query
  })
}

// 查询详细
export function getCouponProductRelation(id) {
  return request({
    url: '/marketing/couponProductRelation/' + id,
    method: 'get'
  })
}

// 新增
export function addCouponProductRelation(data) {
  return request({
    url: '/marketing/couponProductRelation',
    method: 'post',
    data: data
  })
}

// 修改
export function updateCouponProductRelation(data) {
  return request({
    url: '/marketing/couponProductRelation',
    method: 'put',
    data: data
  })
}

// 删除
export function delCouponProductRelation(id) {
  return request({
    url: '/marketing/couponProductRelation/' + id,
    method: 'delete'
  })
}

// 导出
export function exportCouponProductRelation(query) {
  return request({
    url: '/marketing/couponProductRelation/exportXls',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}